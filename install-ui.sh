if [ -z "$1" ] 
then
  echo Supply a git download url to install the UI
  exit 0 
fi
echo Installing UI $1
git clone $1 src/main/webapp/WEB-INF
cd src/main/webapp/WEB-INF
npm install
cd ../../../..
