ffmpeg \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lion_big.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1280"x"720":d=5 \
    -filter_complex \
        "[0:v]trim=168.0:172.0,setpts=PTS-STARTPTS[hv]; \
[0:a]atrim=168.0:172.0,asetpts=PTS-STARTPTS[ha]; \
[hv]drawtext=fontfile=komikax.ttf:text='theapache64': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5:boxborderw=10: x=(w-text_w-10): y=(h-text_h-(text_h/2))[hvw]; \
[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='theapache64', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='Feb 20 2020' \
[vi];\
[1:a]atrim=0.0:5.0,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ai]; [0:v]trim=0:17.15200000000001,setpts=PTS-STARTPTS[v1]; \
[0:a]atrim=0:17.15200000000001,asetpts=PTS-STARTPTS[a1]; \
[0:v]trim=17.15200000000001:79.61600000000006,setpts=0.25*(PTS-STARTPTS)[tv2]; \
[1:a]atrim=6.0:21.61600000000001,asetpts=PTS-STARTPTS,afade=t=in:d=5,afade=t=out:st=14.61600000000001,asetpts=PTS-STARTPTS[ta2]; \
[0:v]trim=79.61600000000006:85.76000000000006,setpts=PTS-STARTPTS[v3]; \
[0:a]atrim=79.61600000000006:85.76000000000006,asetpts=PTS-STARTPTS[a3];\
[0:v]trim=85.76000000000006:111.61600000000008,setpts=0.25*(PTS-STARTPTS)[tv4]; \
[1:a]atrim=22.61600000000001:29.080000000000016,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=5.464000000000006,asetpts=PTS-STARTPTS[ta4]; \
[0:v]trim=111.61600000000008:117.76000000000009,setpts=PTS-STARTPTS[v5]; \
[0:a]atrim=111.61600000000008:117.76000000000009,asetpts=PTS-STARTPTS[a5];\
[0:v]trim=117.76000000000009:143.6160000000001,setpts=0.25*(PTS-STARTPTS)[tv6]; \
[1:a]atrim=30.080000000000016:36.54400000000002,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=5.464000000000002,asetpts=PTS-STARTPTS[ta6]; \
[0:v]trim=143.6160000000001:149.7600000000001,setpts=PTS-STARTPTS[v7]; \
[0:a]atrim=143.6160000000001:149.7600000000001,asetpts=PTS-STARTPTS[a7];\
[0:v]trim=149.7600000000001:195.07200000000014,setpts=0.25*(PTS-STARTPTS)[tv8]; \
[1:a]atrim=37.54400000000002:48.87200000000003,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=10.32800000000001,asetpts=PTS-STARTPTS[ta8]; \
[0:v]trim=195.07200000000014:196.60800000000015,setpts=PTS-STARTPTS[v9]; \
[0:a]atrim=195.07200000000014:196.60800000000015,asetpts=PTS-STARTPTS[a9];\
[0:v]trim=196.60800000000015:213.762,setpts=0.25*(PTS-STARTPTS)[tv10]; \
[1:a]atrim=49.87200000000003:54.16049999999999,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=3.2884999999999636,asetpts=PTS-STARTPTS[ta10]; \
[0:v]trim=213.762,setpts=PTS-STARTPTS[v11]; \
[0:a]atrim=213.762,asetpts=PTS-STARTPTS[a11];\
[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='Thank you!', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='theapache64' \
[cv];\
[1:a]atrim=55.16049999999999:60.16049999999999,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ca]; \
[v1][a1][tv2][ta2][v3][a3][tv4][ta4][v5][a5][tv6][ta6][v7][a7][tv8][ta8][v9][a9][tv10][ta10][v11][a11]concat=n=11:v=1:a=1[woiv][woia]; \
[woiv]drawtext=fontfile=komikax.ttf:text='theapache64': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5:boxborderw=10: x=(w-text_w-10): y=(h-text_h-(text_h/2))[woivw];\
[hvw][ha][vi][ai][woivw][woia][cv][ca]concat=n=4:v=1:a=1" \
-preset superfast auto_lion_big.mp4