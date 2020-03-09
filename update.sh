LATEST_VERSION="v1.0.0-alpha01"

echo "⚙️ Updating..." &&
wget "https://github.com/teamxenox/auto-motion/releases/download/$LATEST_VERSION/auto-motion-$LATEST_VERSION.zip"  -q --show-progress -O "auto-motion-$LATEST_VERSION.zip" &&
sudo rm -R "auto-motion" &&
unzip "auto-motion-$LATEST_VERSION.zip" -d "auto-motion" &&
echo "➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️"
echo "👍 auto-motion updated!"
echo "➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️➡️"



