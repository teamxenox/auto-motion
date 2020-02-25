ffpb -y \
    -i "/home/theapache64/Videos/issue.mp4" \
    -i "/home/theapache64/Documents/projects/auto-motion/lab/lost_in_time.mp3" \
    -f lavfi -i color=c=black:s="1366"x"768":d=5.0 \
    -filter_complex \
        "[2:v] 
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
    :text='Feb 25 2020' 
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
[2:v] 
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=30:fontcolor=white:x=(w-text_w)/2:y=(h-text_h-text_h)/2:text='theapache64', 
  drawtext=fontfile='/home/theapache64/Documents/projects/auto-motion/lab/komikax.ttf':fontsize=15:fontcolor=gray:x=(w-text_w)/2:y=(h+text_h)/2:text='Thank You!' 
[cv];
[1:a]atrim=6.0:11.0,asetpts=PTS-STARTPTS,afade=t=in:d=1,afade=t=out:st=4.0,asetpts=PTS-STARTPTS[ca]; 
[0:v][0:a]concat=
    n=1
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
[vi][ai]
[woivw][woia]
[cv][ca]
    concat=n=3:v=1:a=1" \
-preset superfast "/home/theapache64/Videos/auto_issue.mp4"