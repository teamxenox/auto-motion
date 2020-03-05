package com.theapache64.automotion.core

import com.sun.org.apache.xpath.internal.operations.Bool
import com.theapache64.automotion.models.SubtitleReport
import com.theapache64.automotion.models.Timelapse
import java.io.File
import java.lang.StringBuilder

/**
 * To build ffmpeg final command
 */
class CommandCook(
    private val inputVideo: File,
    private val bgm: File,
    private val subtitleReport: SubtitleReport,

    private val introDuration: Double,
    private val creditsDuration: Double,

    private val timelapseSpeed: Float,

    private val watermark: String,
    private val watermarkTextColor: String,
    private val watermarkFontSize: Int,
    private val watermarkBgColor: String,
    private val watermarkBgOpacity: Float,

    private val introTitle: String,
    private val introSubTitle: String,
    private val creditsTitle: String,
    private val creditsSubTitle: String,
    private val titleFontSize: Int,
    private val subTitleFontSize: Int,
    private val titleColor: String,
    private val subTitleColor: String,

    private val fontFile: File,
    private val bgColor: String,
    private val isRawFFmpeg: Boolean,
    private val isSuperFast: Boolean,
    private val highlightSection: Pair<Double, Double>?
) {

    companion object {
        private const val VIDEO_PREFIX = "v"
        private const val AUDIO_PREFIX = "a"
        private const val TIMELAPSE_VIDEO_PREFIX = "tv"
        private const val TIMELAPSE_AUDIO_PREFIX = "ta"
        private const val INTRO_VIDEO_LABEL = "vi"
        private const val INTRO_AUDIO_LABEL = "ai"
        private const val CREDITS_VIDEO_LABEL = "cv"
        private const val CREDITS_AUDIO_LABEL = "ca"
        private const val WITHOUT_INTRO_VIDEO_LABEL = "woiv"
        private const val WITHOUT_INTRO_VIDEO_LABEL_WITH_WATERMARK_LABEL = "woivw"
        private const val WITHOUT_INTRO_AUDIO_LABEL = "woia"
        private const val HIGHLIGHT_VIDEO_LABEL = "hv"
        private const val HIGHLIGHT_VIDEO_WITH_WATERMARK__LABEL = "hvw"
        private const val HIGHLIGHT_AUDIO_LABEL = "ha"
    }

    private val videoDimens = FileUtils.getDimension(inputVideo)
    private val bgmProvider = BgmProvider(bgm)
    val outputFileName = "${inputVideo.absoluteFile.parentFile.absolutePath}/auto_${inputVideo.name}"
    private lateinit var sb: StringBuilder

    /**
     * Analyze the inputs and generate ffmpeg commmand from it.
     */
    fun prepareCommand(): String {

        sb = StringBuilder()

        /**
         * Init ffmpeg command
         */
        initCommand()

        /**
         * Adding highlight section to start of the video
         */
        addHighlight()

        /**
         * Adding intro video
         */
        addIntro()

        /**
         * Adding timelapse sections and returning it's id
         */
        val videoAudioIds = addTimelapse()

        /**
         * Adding credits (end-title)
         */
        addCredits()

        /**
         * concatenate the videos
         */
        concatWithoutIntroVideos(videoAudioIds)

        /**
         * Add watermark
         */
        addWatermark()

        // Final concat
        var count = 3
        if (highlightSection != null) {
            // adding highlight section
            sb.append("[$HIGHLIGHT_VIDEO_WITH_WATERMARK__LABEL][$HIGHLIGHT_AUDIO_LABEL]")
            count = 4
        }

        sb.append(
            """
            [$INTRO_VIDEO_LABEL][$INTRO_AUDIO_LABEL]
            [$WITHOUT_INTRO_VIDEO_LABEL_WITH_WATERMARK_LABEL][$WITHOUT_INTRO_AUDIO_LABEL]
            [$CREDITS_VIDEO_LABEL][$CREDITS_AUDIO_LABEL]
                concat=n=$count:v=1:a=1" \

        """.trimIndent()
        )

        /**
         * TODO : Remove superfast after development
         */
        if (isSuperFast) {
            sb.append("-preset superfast")
        }

        sb.append(" \"$outputFileName\"")

        return sb.toString();
    }


    private fun concatWithoutIntroVideos(videoAudioIds: MutableList<String>) {
        // Concat without intro video
        if (videoAudioIds.isEmpty()) {
            videoAudioIds.add("[0:v][0:a]")
        }

        for (vaIds in videoAudioIds) {
            sb.append(vaIds)
        }

        sb.append(
            """
            concat=
                n=${videoAudioIds.size}
                :v=1
                :a=1
                [${WITHOUT_INTRO_VIDEO_LABEL}][${WITHOUT_INTRO_AUDIO_LABEL}];
        """.trimIndent()
        )
    }

    private fun addWatermark() {
        sb.append(
            """
            [$WITHOUT_INTRO_VIDEO_LABEL]
                drawtext=
                    fontfile='${fontFile.absolutePath}'
                    :text='$watermark'
                    :fontcolor=$watermarkTextColor
                    :fontsize=${watermarkFontSize}
                    :box=1
                    :boxcolor=$watermarkBgColor@$watermarkBgOpacity
                    :boxborderw=10
                    :x=(w-text_w-10)
                    :y=(h-text_h-(text_h/2))
            [${WITHOUT_INTRO_VIDEO_LABEL_WITH_WATERMARK_LABEL}];

        """.trimIndent()
        )
    }

    private fun addCredits() {

        val creditsBgm = bgmProvider.getBgm(creditsDuration.toDouble()).interval

        // adding credits
        sb.append(
            """
            [2:v] 
              drawtext=fontfile='${fontFile.absolutePath}':fontsize=$titleFontSize:fontcolor=$titleColor:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='$creditsTitle', 
              drawtext=fontfile='${fontFile.absolutePath}':fontsize=$subTitleFontSize:fontcolor=$subTitleColor:x=(w-text_w)/2:y=(h+text_h)/2:text='$creditsSubTitle' 
            [${CREDITS_VIDEO_LABEL}];
            [1:a]atrim=${creditsBgm.first}:${creditsBgm.second},asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=${getAudioFadeOut(
                creditsDuration
            )},asetpts=PTS-STARTPTS[${CREDITS_AUDIO_LABEL}]; 
            
        """.trimIndent()
        )

    }

    private fun addTimelapse(): MutableList<String> {

        // Looping through timelapses and trimming video
        val timelapses = subtitleReport.timelapses
        var sectionId = 0
        var prevTl: Timelapse? = null
        val videoAudioIds = mutableListOf<String>()
        for (timelapseWithIndex in timelapses.withIndex()) {

            val tl = timelapseWithIndex.value
            val bgm = bgmProvider.getBgm(tl.targetDuration).interval


            // Creating labels
            val videoLabel = """${VIDEO_PREFIX}${++sectionId}"""
            val audioLabel = """${AUDIO_PREFIX}${sectionId}"""

            // adding timelapse
            val tvLabel = """${TIMELAPSE_VIDEO_PREFIX}${++sectionId}"""
            val taLabel = """${TIMELAPSE_AUDIO_PREFIX}${sectionId}"""

            if (timelapseWithIndex.index == 0) {
                // first timelapseWithIndex

                // adding normal video before timelapseWithIndex
                sb.append(
                    """
                    [0:v]trim=0:${tl.sourceStart},setpts=PTS-STARTPTS[$videoLabel]; 
                    [0:a]atrim=0:${tl.sourceStart},asetpts=PTS-STARTPTS[$audioLabel]; 
                    
            """.trimIndent()
                )



                sb.append(
                    """
                    [0:v]trim=${tl.sourceStart}:${tl.sourceEnd},setpts=$timelapseSpeed*(PTS-STARTPTS)[$tvLabel]; 
                    [1:a]atrim=${bgm.first}:${bgm.second},asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=${getAudioFadeOut(
                        tl.targetDuration
                    )},asetpts=PTS-STARTPTS[$taLabel]; 
                    
                """.trimIndent()
                )


            } else {


                // adding normal video
                sb.append(
                    """
                     [0:v]trim=${prevTl!!.sourceEnd}:${tl.sourceStart},setpts=PTS-STARTPTS[${videoLabel}]; 
                     [0:a]atrim=${prevTl.sourceEnd}:${tl.sourceStart},asetpts=PTS-STARTPTS[${audioLabel}];
                     
                """.trimIndent()
                )


                // adding timelapse
                sb.append(
                    """
                    [0:v]trim=${tl.sourceStart}:${tl.sourceEnd},setpts=$timelapseSpeed*(PTS-STARTPTS)[$tvLabel]; 
                    [1:a]atrim=${bgm.first}:${bgm.second},asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=${getAudioFadeOut(
                        tl.targetDuration
                    )},asetpts=PTS-STARTPTS[$taLabel]; 
                    
                """.trimIndent()
                )
            }

            videoAudioIds.add("[$videoLabel][$audioLabel]")
            videoAudioIds.add("[$tvLabel][$taLabel]")

            prevTl = tl
        }

        // Add video ending
        if (prevTl != null) {

            // Creating labels
            val videoLabel = """${VIDEO_PREFIX}${++sectionId}"""
            val audioLabel = """${AUDIO_PREFIX}${sectionId}"""

            // normal video
            sb.append(
                """
                     [0:v]
                        trim=${prevTl.sourceEnd},
                        setpts=PTS-STARTPTS
                     [${videoLabel}]; 
                     
                     [0:a]
                        atrim=${prevTl.sourceEnd},
                        asetpts=PTS-STARTPTS
                     [${audioLabel}];
                     
                """.trimIndent()
            )

            videoAudioIds.add("[$videoLabel][$audioLabel]")
        }

        return videoAudioIds
    }

    private fun getAudioFadeOut(targetDuration: Double): Double {

        return when {
            targetDuration <= 2 -> {
                targetDuration - (targetDuration * 0.10)
            }

            targetDuration in 2.1..5.0 -> {
                targetDuration - 0.5
            }

            else -> {
                targetDuration - 1
            }
        }
    }

    private fun addIntro() {

        val introBgm = bgmProvider.getBgm(introDuration).interval

        // Adding intro
        sb.append(
            """
            [2:v] 
              drawtext=
                fontfile='${fontFile.absolutePath}'
                :fontsize=$titleFontSize
                :fontcolor=$titleColor
                :x=(w-text_w)/2
                :y=(h-text_h-text_h)/2
                :text='$introTitle', 
              drawtext=
                fontfile='${fontFile.absolutePath}'
                :fontsize=$subTitleFontSize
                :fontcolor=$subTitleColor
                :x=(w-text_w)/2
                :y=(h+text_h)/2
                :text='$introSubTitle' 
            [$INTRO_VIDEO_LABEL];
            
            [1:a]
                atrim=${introBgm.first}:${introBgm.second},
                afade=
                    t=in
                    :d=1,
                afade=
                    t=out
                    :st=${getAudioFadeOut(introDuration)},
                asetpts=PTS-STARTPTS
            [$INTRO_AUDIO_LABEL]; 
            
        """.trimIndent()
        )

    }

    private fun addHighlight() {
        if (highlightSection != null) {
            sb.append(
                """
                            [0:v]
                                trim=${highlightSection.first}:${highlightSection.second},
                                setpts=PTS-STARTPTS
                            [$HIGHLIGHT_VIDEO_LABEL]; 
                            
                            [0:a]
                                atrim=${highlightSection.first}:${highlightSection.second},
                                asetpts=PTS-STARTPTS
                            [$HIGHLIGHT_AUDIO_LABEL]; 
                                 
                            [$HIGHLIGHT_VIDEO_LABEL]
                                drawtext=
                                    fontfile='${fontFile.absolutePath}'
                                    :text='$watermark'
                                    :fontcolor=$watermarkTextColor
                                    :fontsize=$watermarkFontSize
                                    :box=1
                                    :boxcolor=$watermarkBgColor@$watermarkBgOpacity
                                    :boxborderw=10
                                    :x=(w-text_w-10)
                                    :y=(h-text_h-(text_h/2))
                            [$HIGHLIGHT_VIDEO_WITH_WATERMARK__LABEL]; 
                            
                """.trimIndent()
            )
        }
    }

    private fun initCommand() {

        val program = if (isRawFFmpeg) {
            "ffmpeg"
        } else {
            "ffpb"
        }

        sb.append(
            """
            $program -y \
                -i "${inputVideo.absolutePath}" \
                -i "${bgm.absolutePath}" \
                -f lavfi -i color=c=$bgColor:s="${videoDimens.width}"x"${videoDimens.height}":sar=${videoDimens.sampleAspectRatio.replace(
                ":",
                "/"
            )}:d=$introDuration \
                -filter_complex \
                    "
        """.trimIndent()
        )
    }

}