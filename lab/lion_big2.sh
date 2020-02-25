ffmpeg \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lion_big2.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1280"x"720":d=5 \
    -filter_complex \
        "[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='theapache64', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='Feb 20 2020' \
[vi];\
[1:a]atrim=0.0:5.0,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ai]; [0:v]trim=0:42.49600000000003,setpts=PTS-STARTPTS[v1]; \
[0:a]atrim=0:42.49600000000003,asetpts=PTS-STARTPTS[a1]; \
[0:v]trim=42.49600000000003:55.04000000000004,setpts=0.25*(PTS-STARTPTS)[tv2]; \
[1:a]atrim=6.0:9.136000000000003,asetpts=PTS-STARTPTS,afade=t=in:d=5,afade=t=out:st=2.136000000000003,asetpts=PTS-STARTPTS[ta2]; \
[0:v]trim=55.04000000000004:156.6720000000001,setpts=PTS-STARTPTS[v3]; \
[0:a]atrim=55.04000000000004:156.6720000000001,asetpts=PTS-STARTPTS[a3];\
[0:v]trim=156.6720000000001:169.72800000000012,setpts=0.25*(PTS-STARTPTS)[tv4]; \
[1:a]atrim=10.136000000000003:13.400000000000006,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=2.264000000000003,asetpts=PTS-STARTPTS[ta4]; \
[0:v]trim=169.72800000000012:176.12800000000013,setpts=PTS-STARTPTS[v5]; \
[0:a]atrim=169.72800000000012:176.12800000000013,asetpts=PTS-STARTPTS[a5];\
[0:v]trim=176.12800000000013:212.22400000000016,setpts=0.25*(PTS-STARTPTS)[tv6]; \
[1:a]atrim=14.400000000000006:23.424000000000014,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=8.024000000000008,asetpts=PTS-STARTPTS[ta6]; \
[0:v]trim=212.22400000000016:230.40000000000018,setpts=PTS-STARTPTS[v7]; \
[0:a]atrim=212.22400000000016:230.40000000000018,asetpts=PTS-STARTPTS[a7];\
[0:v]trim=230.40000000000018:241.6640000000002,setpts=0.25*(PTS-STARTPTS)[tv8]; \
[1:a]atrim=24.424000000000014:27.240000000000016,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.8160000000000025,asetpts=PTS-STARTPTS[ta8]; \
[0:v]trim=241.6640000000002:247.8080000000002,setpts=PTS-STARTPTS[v9]; \
[0:a]atrim=241.6640000000002:247.8080000000002,asetpts=PTS-STARTPTS[a9];\
[0:v]trim=247.8080000000002:258.81599999999986,setpts=0.25*(PTS-STARTPTS)[tv10]; \
[1:a]atrim=28.240000000000016:30.991999999999933,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.7519999999999172,asetpts=PTS-STARTPTS[ta10]; \
[0:v]trim=258.81599999999986:272.1279999999984,setpts=PTS-STARTPTS[v11]; \
[0:a]atrim=258.81599999999986:272.1279999999984,asetpts=PTS-STARTPTS[a11];\
[0:v]trim=272.1279999999984:285.18399999999696,setpts=0.25*(PTS-STARTPTS)[tv12]; \
[1:a]atrim=31.991999999999933:35.255999999999574,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=2.2639999999996405,asetpts=PTS-STARTPTS[ta12]; \
[0:v]trim=285.18399999999696:287.99999999999665,setpts=PTS-STARTPTS[v13]; \
[0:a]atrim=285.18399999999696:287.99999999999665,asetpts=PTS-STARTPTS[a13];\
[0:v]trim=287.99999999999665:313.0879999999939,setpts=0.25*(PTS-STARTPTS)[tv14]; \
[1:a]atrim=36.255999999999574:42.52799999999888,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=5.271999999999309,asetpts=PTS-STARTPTS[ta14]; \
[0:v]trim=313.0879999999939:348.15999999999,setpts=PTS-STARTPTS[v15]; \
[0:a]atrim=313.0879999999939:348.15999999999,asetpts=PTS-STARTPTS[a15];\
[0:v]trim=348.15999999999:359.4239999999888,setpts=0.25*(PTS-STARTPTS)[tv16]; \
[1:a]atrim=43.52799999999888:46.34399999999857,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.8159999999996899,asetpts=PTS-STARTPTS[ta16]; \
[0:v]trim=359.4239999999888:456.70399999997807,setpts=PTS-STARTPTS[v17]; \
[0:a]atrim=359.4239999999888:456.70399999997807,asetpts=PTS-STARTPTS[a17];\
[0:v]trim=456.70399999997807:468.47999999997677,setpts=0.25*(PTS-STARTPTS)[tv18]; \
[1:a]atrim=47.34399999999857:50.28799999999825,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.9439999999996758,asetpts=PTS-STARTPTS[ta18]; \
[0:v]trim=468.47999999997677:483.5839999999751,setpts=PTS-STARTPTS[v19]; \
[0:a]atrim=468.47999999997677:483.5839999999751,asetpts=PTS-STARTPTS[a19];\
[0:v]trim=483.5839999999751:494.07999999997395,setpts=0.25*(PTS-STARTPTS)[tv20]; \
[1:a]atrim=51.28799999999825:53.91199999999796,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.623999999999711,asetpts=PTS-STARTPTS[ta20]; \
[0:v]trim=494.07999999997395:504.5759999999728,setpts=PTS-STARTPTS[v21]; \
[0:a]atrim=494.07999999997395:504.5759999999728,asetpts=PTS-STARTPTS[a21];\
[0:v]trim=504.5759999999728:514.0479999999718,setpts=0.25*(PTS-STARTPTS)[tv22]; \
[1:a]atrim=54.91199999999796:57.27999999999771,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.3679999999997534,asetpts=PTS-STARTPTS[ta22]; \
[0:v]trim=514.0479999999718:526.3359999999705,setpts=PTS-STARTPTS[v23]; \
[0:a]atrim=514.0479999999718:526.3359999999705,asetpts=PTS-STARTPTS[a23];\
[0:v]trim=526.3359999999705:542.9759999999686,setpts=0.25*(PTS-STARTPTS)[tv24]; \
[1:a]atrim=58.27999999999771:62.439999999997255,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=3.159999999999542,asetpts=PTS-STARTPTS[ta24]; \
[0:v]trim=542.9759999999686:565.5039999999661,setpts=PTS-STARTPTS[v25]; \
[0:a]atrim=542.9759999999686:565.5039999999661,asetpts=PTS-STARTPTS[a25];\
[0:v]trim=565.5039999999661:598.7839999999625,setpts=0.25*(PTS-STARTPTS)[tv26]; \
[1:a]atrim=63.439999999997255:71.75999999999634,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=7.319999999999084,asetpts=PTS-STARTPTS[ta26]; \
[0:v]trim=598.7839999999625:626.1759999999595,setpts=PTS-STARTPTS[v27]; \
[0:a]atrim=598.7839999999625:626.1759999999595,asetpts=PTS-STARTPTS[a27];\
[0:v]trim=626.1759999999595:634.3679999999586,setpts=0.25*(PTS-STARTPTS)[tv28]; \
[1:a]atrim=72.75999999999634:74.80799999999611,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=1.0479999999997744,asetpts=PTS-STARTPTS[ta28]; \
[0:v]trim=634.3679999999586:649.2159999999569,setpts=PTS-STARTPTS[v29]; \
[0:a]atrim=634.3679999999586:649.2159999999569,asetpts=PTS-STARTPTS[a29];\
[0:v]trim=649.2159999999569:662.0159999999555,setpts=0.25*(PTS-STARTPTS)[tv30]; \
[1:a]atrim=75.80799999999611:79.00799999999576,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=2.1999999999996476,asetpts=PTS-STARTPTS[ta30]; \
[0:v]trim=662.0159999999555:668.1599999999548,setpts=PTS-STARTPTS[v31]; \
[0:a]atrim=662.0159999999555:668.1599999999548,asetpts=PTS-STARTPTS[a31];\
[0:v]trim=668.1599999999548:697.0879999999516,setpts=0.25*(PTS-STARTPTS)[tv32]; \
[1:a]atrim=80.00799999999576:87.23999999999496,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=6.2319999999992035,asetpts=PTS-STARTPTS[ta32]; \
[0:v]trim=697.0879999999516:703.231999999951,setpts=PTS-STARTPTS[v33]; \
[0:a]atrim=697.0879999999516:703.231999999951,asetpts=PTS-STARTPTS[a33];\
[0:v]trim=703.231999999951:727.2959999999483,setpts=0.25*(PTS-STARTPTS)[tv34]; \
[1:a]atrim=88.23999999999496:94.2559999999943,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=5.015999999999337,asetpts=PTS-STARTPTS[ta34]; \
[0:v]trim=727.2959999999483:765.4399999999441,setpts=PTS-STARTPTS[v35]; \
[0:a]atrim=727.2959999999483:765.4399999999441,asetpts=PTS-STARTPTS[a35];\
[0:v]trim=765.4399999999441:831.7439999999368,setpts=0.25*(PTS-STARTPTS)[tv36]; \
[1:a]atrim=95.2559999999943:111.83199999999248,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=15.575999999998174,asetpts=PTS-STARTPTS[ta36]; \
[0:v]trim=831.7439999999368:837.8879999999361,setpts=PTS-STARTPTS[v37]; \
[0:a]atrim=831.7439999999368:837.8879999999361,asetpts=PTS-STARTPTS[a37];\
[0:v]trim=837.8879999999361:858.163047,setpts=0.25*(PTS-STARTPTS)[tv38]; \
[1:a]atrim=112.83199999999248:117.90076175000844,asetpts=PTS-STARTPTS,afade=t=in:d=2,afade=t=out:st=4.068761750015966,asetpts=PTS-STARTPTS[ta38]; \
[0:v]trim=858.163047,setpts=PTS-STARTPTS[v39]; \
[0:a]atrim=858.163047,asetpts=PTS-STARTPTS[a39];\
[2:v] \
  drawtext=fontfile=komikax.ttf:fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='Thank you!', \
  drawtext=fontfile=komikax.ttf:fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='theapache64' \
[cv];\
[1:a]atrim=118.90076175000844:123.90076175000844,afade=t=in:d=1,afade=t=out:st=4,asetpts=PTS-STARTPTS[ca];[v1][a1][tv2][ta2][v3][a3][tv4][ta4][v5][a5][tv6][ta6][v7][a7][tv8][ta8][v9][a9][tv10][ta10][v11][a11][tv12][ta12][v13][a13][tv14][ta14][v15][a15][tv16][ta16][v17][a17][tv18][ta18][v19][a19][tv20][ta20][v21][a21][tv22][ta22][v23][a23][tv24][ta24][v25][a25][tv26][ta26][v27][a27][tv28][ta28][v29][a29][tv30][ta30][v31][a31][tv32][ta32][v33][a33][tv34][ta34][v35][a35][tv36][ta36][v37][a37][tv38][ta38][v39][a39]concat=n=39:v=1:a=1[woiv][woia]; \
[woiv]drawtext=fontfile=komikax.ttf:text='theapache64': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5:boxborderw=10: x=(w-text_w-10): y=(h-text_h-(text_h/2))[woivw];\
[vi][ai][woivw][woia][cv][ca]concat=n=3:v=1:a=1" \
-preset superfast auto_lion_big2.mp4