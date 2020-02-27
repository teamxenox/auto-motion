echo "⚙️ Updating..." &&
wget "https://github.com/theapache64/auto-motion/archive/master.zip" -q --show-progress -O "master.zip" &&
unzip master.zip &&
rm -R auto-motion &&
mv auto-motion-master auto-motion &&
echo "👍 auto-motion updated!"



