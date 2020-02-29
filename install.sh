echo "⚙️ Installing..." &&
sudo pip install ffpb &&
# Maybe replace with : pip install git+https://github.com/agermanidis/autosub.git
sudo pip install autosub --ignore-installed six &&
wget "https://github.com/theapache64/auto-motion/archive/master.zip" -q --show-progress -O "master.zip" &&
unzip master.zip &&
mv auto-motion-master auto-motion &&
curDir=$PWD &&
echo "\nalias auto-motion='java -jar $curDir/auto-motion/auto-motion.main.jar'" >> ~/.bashrc &&
echo "👍 auto-motion installed!"



