ffmpeg -y \
  -f lavfi -i color=c=black:s="1366"x"768":d=2 \
  -i issue.mp4 \
  -i issue_2.mp4 \
  -filter_complex " \
  [1:v][1:a][2:v][2:a]concat=n=2:a=1:v=1[out] \
  " \
  -map "[out]" issues.mp4

