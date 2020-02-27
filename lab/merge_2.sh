ffmpeg \
  -f concat \
  -i lion.mp4 -i shark.mp4 \
  -codec copy lion_shark_2.mp4

