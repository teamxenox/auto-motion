ffmpeg \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/sample.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1366"x"768":d=3 \
    -filter_complex \
        "[0:v]
    trim=180.0:185.0,
    setpts=PTS-STARTPTS
[hv]; \

[0:a]
    atrim=180.0:185.0,
    asetpts=PTS-STARTPTS
[ha]; \
     
[hv]
    drawtext=
        fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
        :text='theapache64'
        :fontcolor=white
        :fontsize=24
        :box=1
        :boxcolor=black@0.5
        :boxborderw=10
        :x=(w-text_w-10)
        :y=(h-text_h-(text_h/2))
[hvw]; \
[2:v] \
  drawtext=
    fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
    :fontsize=30
    :fontcolor=white
    :x=(w-text_w)/2
    :y=(h-text_h-text_h)/2
    :text='theapache64', \
  drawtext=
    fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
    :fontsize=15
    :fontcolor=gray
    :x=(w-text_w)/2
    :y=(h+text_h)/2
    :text='Feb 23 2020' \
[vi];\

[1:a]
    atrim=0.0:3.0,
    afade=
        t=in
        :d=1,
    afade=
        t=out
        :st=2,
    asetpts=PTS-STARTPTS
[ai]; 
[0:v]trim=0:12.80000000000001,setpts=PTS-STARTPTS[v1]; \
[0:a]atrim=0:12.80000000000001,asetpts=PTS-STARTPTS[a1]; \
[0:v]trim=12.80000000000001:71.16800000000005,setpts=0.25*(PTS-STARTPTS)[tv2]; \
[1:a]atrim=4.0:18.59200000000001,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=13.59200000000001,asetpts=PTS-STARTPTS[ta2]; \
[0:v]trim=71.16800000000005:93.95200000000007,setpts=PTS-STARTPTS[v3]; \
[0:a]atrim=71.16800000000005:93.95200000000007,asetpts=PTS-STARTPTS[a3];\
[0:v]trim=93.95200000000007:102.91200000000008,setpts=0.25*(PTS-STARTPTS)[tv4]; \
[1:a]atrim=19.59200000000001:21.83200000000001,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=1.240000000000002,asetpts=PTS-STARTPTS[ta4]; \
[0:v]trim=102.91200000000008:105.72800000000008,setpts=PTS-STARTPTS[v5]; \
[0:a]atrim=102.91200000000008:105.72800000000008,asetpts=PTS-STARTPTS[a5];\
[0:v]trim=105.72800000000008:126.2080000000001,setpts=0.25*(PTS-STARTPTS)[tv6]; \
[1:a]atrim=22.83200000000001:27.952000000000016,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=4.1200000000000045,asetpts=PTS-STARTPTS[ta6]; \
[0:v]trim=126.2080000000001:137.2160000000001,setpts=PTS-STARTPTS[v7]; \
[0:a]atrim=126.2080000000001:137.2160000000001,asetpts=PTS-STARTPTS[a7];\
[0:v]trim=137.2160000000001:186.62400000000014,setpts=0.25*(PTS-STARTPTS)[tv8]; \
[1:a]atrim=28.952000000000016:41.30400000000003,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=11.352000000000011,asetpts=PTS-STARTPTS[ta8]; \
[0:v]trim=186.62400000000014:197.63200000000015,setpts=PTS-STARTPTS[v9]; \
[0:a]atrim=186.62400000000014:197.63200000000015,asetpts=PTS-STARTPTS[a9];\
[0:v]trim=197.63200000000015:210.43200000000016,setpts=0.25*(PTS-STARTPTS)[tv10]; \
[1:a]atrim=42.30400000000003:45.50400000000003,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=2.200000000000003,asetpts=PTS-STARTPTS[ta10]; \
[0:v]trim=210.43200000000016:217.34400000000016,setpts=PTS-STARTPTS[v11]; \
[0:a]atrim=210.43200000000016:217.34400000000016,asetpts=PTS-STARTPTS[a11];\
[0:v]trim=217.34400000000016:264.44799999999924,setpts=0.25*(PTS-STARTPTS)[tv12]; \
[1:a]atrim=46.50400000000003:58.2799999999998,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=10.775999999999769,asetpts=PTS-STARTPTS[ta12]; \
[0:v]trim=264.44799999999924:284.671999999997,setpts=PTS-STARTPTS[v13]; \
[0:a]atrim=264.44799999999924:284.671999999997,asetpts=PTS-STARTPTS[a13];\
[0:v]trim=284.671999999997:469.7599999999766,setpts=0.25*(PTS-STARTPTS)[tv14]; \
[1:a]atrim=59.2799999999998:105.5519999999947,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=45.271999999994904,asetpts=PTS-STARTPTS[ta14]; \
[0:v]
   trim=469.7599999999766,
   setpts=PTS-STARTPTS
[v15]; \

[0:a]
   atrim=469.7599999999766,
   asetpts=PTS-STARTPTS
[a15];\
[2:v] \
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='Thank You!', \
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='theapache64' \
[cv];\
[1:a]atrim=106.5519999999947:109.5519999999947,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=2,asetpts=PTS-STARTPTS[ca]; \
[v1][a1][tv2][ta2][v3][a3][tv4][ta4][v5][a5][tv6][ta6][v7][a7][tv8][ta8][v9][a9][tv10][ta10][v11][a11][tv12][ta12][v13][a13][tv14][ta14][v15][a15]concat=
    n=15
    :v=1
    :a=1
    [woiv][woia];[woiv]
    drawtext=
        fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
        :text='theapache64'
        :fontcolor=white
        :fontsize=24
        :box=1
        :boxcolor=black@0.5
        :boxborderw=10
        :x=(w-text_w-10)
        :y=(h-text_h-(text_h/2))
[woivw];\
[hvw][ha][vi][ai][woivw][woia][cv][ca]concat=n=4:v=1:a=1" \
-preset superfast auto_sample.mp4