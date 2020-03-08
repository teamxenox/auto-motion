# auto-motion
A CLI program to edit your videos, instantly, with minimum input.

## Dependencies 💢

 - [ffmpeg](https://ffmpeg.org) (>=4.2.2)
 - [python](https://pip.pypa.io/en/stable/)

## Install 🛠

```shell script
wget "https://raw.githubusercontent.com/theapache64/auto-motion/master/install.sh" -q --show-progress -O install.sh && sh install.sh && source ~/.bashhrc
```


## Usage 🖥

```
usage: auto-motion -v input.mp4 [-H] -V <arg> [-BGM <arg>] [-ST <arg>]
       [-VL <arg>] [-MTL <arg>] [-TLS <arg>] [-ID <arg>] [-CR <arg>] [-WM
       <arg>] [-IT <arg>] [-CT <arg>] [-IST <arg>] [-CST <arg>] [-F <arg>]
       [-HL <arg>] [-WMC <arg>] [-WMS <arg>] [-WMBG <arg>] [-WMBGO <arg>]
       [-TFS <arg>] [-STFS <arg>] [-TC <arg>] [-STC <arg>] [-BG <arg>]
       [-RSRT <arg>] [-DSRT] [-RFMPG] [-sf] [-KS]
A tool to edit your lengthy screen records, automatically. Version :
v1.0.0-alpha01
 -H,--help                              To print help text
 -V,--video <arg>                       Video inputs (required at least
                                        one)
 -BGM,--background-music <arg>          Background music for timelapse.
                                        Default
                                        '/home/theapache64/Documents/proje
                                        cts/auto-motion/lab/lost_in_time.m
                                        p3'
 -ST,--sub-title <arg>                  Intro sub title
 -VL,--video-lang <arg>                 Video language. Default 'en'
 -MTL,--min-tl-src-len <arg>            Minimum timelapse source length
                                        (in seconds). Default '2.0'
 -TLS,--timelapse-speed <arg>           Timelapse speed (must be < 1). 0.5
                                        = 2x speed, 0.25 = 4x. Default
                                        '0.25'
 -ID,--intro-duration <arg>             Intro duration (in seconds).
                                        Default '3.0'
 -CR,--credits-duration <arg>           Credits duration (in seconds).
                                        Default '2'
 -WM,--watermark <arg>                  Watermark text. Default
                                        (theapache64) (active username)
 -IT,--intro-title <arg>                Intro title. Default (theapache64)
                                        (active username)
 -CT,--credits-title <arg>              Credits title. Default 'Thank
                                        You!'
 -IST,--intro-sub-title <arg>           Intro sub title. Default 'Mar 05
                                        2020' (current date)
 -CST,--credits-sub-title <arg>         Credits sub title. Default
                                        (theapache64) (active username)
 -F,--font <arg>                        Font file path. Default
                                        '/home/theapache64/Documents/proje
                                        cts/auto-motion/lab/komikax.ttf'
 -HL,--highlight <arg>                  Highlight of the video. Format
                                        'HH:mm:ss-ss' (from- to seconds).
                                        Eg:
                                        auto-motion -v input.mp4 -HL
                                        '00:01:00-5'
                                        Will highlight 5 seconds of clip
                                        from 00:01:00
 -WMC,--wm-color <arg>                  Watermark text color. Default
                                        'white'
 -WMS,--wm-size <arg>                   Watermark text size. Default '24'
 -WMBG,--wm-background-color <arg>      Watermark background color.
                                        Default 'black'
 -WMBGO,--wm-background-opacity <arg>   Watermark background opacity.
                                        Default '0.5'
 -TFS,--title-font-size <arg>           Title font size. Default '30'
 -STFS,--sub-title-font-size <arg>      Sub title font size. Default '15'
 -TC,--title-color <arg>                Title color. Default 'white'
 -STC,--sub-title-color <arg>           Sub title color. Default 'gray'
 -BG,--background-color <arg>           Background color. Default 'black'
 -RSRT,--raw-srt <arg>                  To cancel autosub usage and use
                                        passed SRT file for timelapse
                                        calculation
 -DSRT,--default-srt                    To cancel autosub usage and use
                                        default SRT of the input video
                                        file.
 -RFMPG,--raw-ffmpeg                    To use ffmpeg rather than ffpb
 -sf,--superfast                        To make the ffmpeg encodig preset
                                        to superfast
 -KS,--keep-sh                          To keep final shell script file
                                        (developer-option). Default false.
🎊 Happy automate!
```


## Update ⬆

```shell script
wget "https://raw.githubusercontent.com/theapache64/auto-motion/master/update.sh" -q --show-progress -O update.sh && sh update.sh
```

## Contributors 🙌
 - [theapache64](https://github.com/theapache64)
