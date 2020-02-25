echo "⚙️ Installing..." &&
pip install --user ffpb &&
pip install autosub &&
wget "https://github.com/theapache64/auto-motion/archive/master.zip" -q --show-progress -O "master.zip" &&
unzip master.zip &&
mv auto-motion-master auto-motion &&
curDir=$PWD &&
echo "\nalias auto-motion='java -jar $curDir/auto-motion/auto-motion_v1.0.0-alpha01.jar'" >> ~/.bash_aliases &&
echo "👍 auto-motion installed!"



