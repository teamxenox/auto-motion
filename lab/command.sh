  VIDEO_WIDTH=1280
  VIDEO_HEIGHT=720
  INTRO_DURATION=5
  TIMELAPSE_SPEED=0.25
  NAME="theapache64"
  DATE="18th February 2020"
  END_TEXT="THANK YOU FOR WATCHING"

  ffpb -y \
    -i lion.mp4 \
    -i lost_in_time.mp3 \
    -f lavfi -i color=c=black:s="$VIDEO_WIDTH"x"$VIDEO_HEIGHT":d=$INTRO_DURATION \
    -filter_complex \
       "[2:v] \
          drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='$NAME', \
          drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='$DATE' \
        [intro];\
        [1:a]atrim=0:5,afade=t=in:d=1,afade=t=out:st=04,asetpts=PTS-STARTPTS[introbgm]; \
        [0:v]trim=0:10,setpts=PTS-STARTPTS[v1]; \
        [0:a]atrim=0:10,asetpts=PTS-STARTPTS[a1]; \
        [0:v]trim=10:30,setpts=$TIMELAPSE_SPEED*(PTS-STARTPTS)[v2]; \
        [1:a]atrim=45:50,asetpts=PTS-STARTPTS,afade=t=in:d=5,afade=t=out:st=14,asetpts=PTS-STARTPTS[bgm1]; \
        [0:v]trim=30:40,setpts=PTS-STARTPTS[v3]; \
        [0:a]atrim=30:40,asetpts=PTS-STARTPTS[a3];\
        [0:v]trim=40,setpts=$TIMELAPSE_SPEED*(PTS-STARTPTS)[v4]; \
        [1:a]atrim=30:35,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=13,asetpts=PTS-STARTPTS[bgm2]; \
        [2:v] \
          drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='$NAME', \
          drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='$END_TEXT' \
        [ending];\
        [1:a]atrim=0:5,afade=t=in:d=1,afade=t=out:st=04,asetpts=PTS-STARTPTS[endingbgm]; \

        [v1][a1][v2][bgm1][v3][a3][v4][bgm2]concat=n=4:v=1:a=1[wointrov][wointroa];\
        [wointrov]drawtext=fontfile=komikax.ttf:text='theapache64': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5:boxborderw=10: x=(w-text_w-10): y=(h-text_h-(text_h/2))[wointrovt];\
        [intro][introbgm][wointrovt][wointroa][ending][endingbgm]concat=n=3:v=1:a=1" \
     -preset superfast output_lion.mp4