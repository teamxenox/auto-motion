ffmpeg \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lion.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1280"x"720":d=5 \
    -filter_complex \
        "[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='theapache64', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='Feb 20 2020' \
[vi];\
[1:a]atrim=0.0:5.0,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ai]; [0:v]trim=0:14.848000000000011,setpts=PTS-STARTPTS[v1]; \
[0:a]atrim=0:14.848000000000011,asetpts=PTS-STARTPTS[a1]; \
[0:v]trim=14.848000000000011:28.41600000000002,setpts=0.25*(PTS-STARTPTS)[tv2]; \
[1:a]atrim=6.0:9.392000000000003,asetpts=PTS-STARTPTS,afade=t=in:d=5,afade=t=out:st=2.3920000000000026,asetpts=PTS-STARTPTS[ta2]; \
[0:v]trim=28.41600000000002:34.560000000000024,setpts=PTS-STARTPTS[v3]; \
[0:a]atrim=28.41600000000002:34.560000000000024,asetpts=PTS-STARTPTS[a3];\
[0:v]trim=34.560000000000024:58.7697,setpts=0.25*(PTS-STARTPTS)[tv4]; \
[1:a]atrim=10.392000000000003:16.444424999999995,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=5.052424999999994,asetpts=PTS-STARTPTS[ta4]; \
[0:v]trim=58.7697,setpts=PTS-STARTPTS[v5]; \
[0:a]atrim=58.7697,asetpts=PTS-STARTPTS[a5];\
[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='Thank you!', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='theapache64' \
[cv];\
[1:a]atrim=17.444424999999995:22.444424999999995,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ca];[v1][a1][tv2][ta2][v3][a3][tv4][ta4][v5][a5]concat=n=5:v=1:a=1[woiv][woia]; \
[woiv]drawtext=fontfile=komikax.ttf:text='theapache64': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5:boxborderw=10: x=(w-text_w-10): y=(h-text_h-(text_h/2))[woivw];\
[vi][ai][woivw][woia][cv][ca]concat=n=3:v=1:a=1" \
-preset superfast auto_lion.mp4