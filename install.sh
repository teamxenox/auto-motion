LATEST_VERSION="v1.0.0-alpha01"

echo "⚙️ Installing..." &&
sudo pip install ffpb &&
sudo pip install git+https://github.com/agermanidis/autosub.git &&
wget "https://github.com/teamxenox/auto-motion/releases/download/$LATEST_VERSION/auto-motion-$LATEST_VERSION.zip" -q --show-progress -O "auto-motion-$LATEST_VERSION.zip" &&
unzip "auto-motion-$LATEST_VERSION.zip" -d "auto-motion" &&
curDir=$PWD &&
echo "\nalias auto-motion='java -jar $curDir/auto-motion/auto-motion.main.jar'" >> ~/.bashrc &&
echo "➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️"
echo "👍 auto-motion installed!"
echo "➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️"


