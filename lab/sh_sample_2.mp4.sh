ffpb -y \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/sample_2.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1366"x"768":d=5.0 \
    -filter_complex \
        "[0:v]
    trim=60.0:65.0,
    setpts=PTS-STARTPTS
[hv]; 

[0:a]
    atrim=60.0:65.0,
    asetpts=PTS-STARTPTS
[ha]; 
     
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
[hvw]; 
[2:v] 
  drawtext=
    fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
    :fontsize=30
    :fontcolor=white
    :x=(w-text_w)/2
    :y=(h-text_h-text_h)/2
    :text='theapache64', 
  drawtext=
    fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf'
    :fontsize=15
    :fontcolor=gray
    :x=(w-text_w)/2
    :y=(h+text_h)/2
    :text='23 Feb 2020' 
[vi];

[1:a]
    atrim=0.0:5.0,
    afade=
        t=in
        :d=1,
    afade=
        t=out
        :st=4.0,
    asetpts=PTS-STARTPTS
[ai]; 
[0:v]trim=0:116.48000000000009,setpts=PTS-STARTPTS[v1]; 
[0:a]atrim=0:116.48000000000009,asetpts=PTS-STARTPTS[a1]; 
[0:v]trim=116.48000000000009:242.4320000000002,setpts=0.25*(PTS-STARTPTS)[tv2]; 
[1:a]atrim=6.0:37.48800000000003,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=30.488000000000024,asetpts=PTS-STARTPTS[ta2]; 
[0:v]trim=242.4320000000002:248.5760000000002,setpts=PTS-STARTPTS[v3]; 
[0:a]atrim=242.4320000000002:248.5760000000002,asetpts=PTS-STARTPTS[a3];
[0:v]trim=248.5760000000002:307.7119999999945,setpts=0.25*(PTS-STARTPTS)[tv4]; 
[1:a]atrim=38.48800000000003:53.2719999999986,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=13.78399999999857,asetpts=PTS-STARTPTS[ta4]; 
[0:v]trim=307.7119999999945:310.52799999999417,setpts=PTS-STARTPTS[v5]; 
[0:a]atrim=307.7119999999945:310.52799999999417,asetpts=PTS-STARTPTS[a5];
[0:v]trim=310.52799999999417:324.35199999999264,setpts=0.25*(PTS-STARTPTS)[tv6]; 
[1:a]atrim=54.2719999999986:57.72799999999822,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=2.4559999999996194,asetpts=PTS-STARTPTS[ta6]; 
[0:v]trim=324.35199999999264:345.5999999999903,setpts=PTS-STARTPTS[v7]; 
[0:a]atrim=324.35199999999264:345.5999999999903,asetpts=PTS-STARTPTS[a7];
[0:v]trim=345.5999999999903:434.6879999999805,setpts=0.25*(PTS-STARTPTS)[tv8]; 
[1:a]atrim=58.72799999999822:80.99999999999577,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=21.271999999997547,asetpts=PTS-STARTPTS[ta8]; 
[0:v]trim=434.6879999999805:445.95199999997925,setpts=PTS-STARTPTS[v9]; 
[0:a]atrim=434.6879999999805:445.95199999997925,asetpts=PTS-STARTPTS[a9];
[0:v]trim=445.95199999997925:507.9039999999724,setpts=0.25*(PTS-STARTPTS)[tv10]; 
[1:a]atrim=81.99999999999577:97.48799999999406,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=14.487999999998294,asetpts=PTS-STARTPTS[ta10]; 
[0:v]trim=507.9039999999724:511.48799999997203,setpts=PTS-STARTPTS[v11]; 
[0:a]atrim=507.9039999999724:511.48799999997203,asetpts=PTS-STARTPTS[a11];
[0:v]trim=511.48799999997203:553.688,setpts=0.25*(PTS-STARTPTS)[tv12]; 
[1:a]atrim=98.48799999999406:109.03800000000105,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=9.550000000006989,asetpts=PTS-STARTPTS[ta12]; 
[0:v]
   trim=553.688,
   setpts=PTS-STARTPTS
[v13]; 

[0:a]
   atrim=553.688,
   asetpts=PTS-STARTPTS
[a13];
[2:v] 
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='Thank you!', 
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='theapache64' 
[cv];
[1:a]atrim=110.03800000000105:115.03800000000105,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=4.0,asetpts=PTS-STARTPTS[ca]; 
[v1][a1][tv2][ta2][v3][a3][tv4][ta4][v5][a5][tv6][ta6][v7][a7][tv8][ta8][v9][a9][tv10][ta10][v11][a11][tv12][ta12][v13][a13]concat=
    n=13
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
[woivw];
[hvw][ha][vi][ai]
[woivw][woia]
[cv][ca]
    concat=n=4:v=1:a=1" \
-preset superfast auto_sample_2.mp4