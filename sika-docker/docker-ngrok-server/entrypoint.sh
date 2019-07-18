#!/bin/sh -e

NGROK_HOME=/usr/local/ngrok

cd $NGROK_HOME

# init ngrok server if build.info is not exist.
if [ ! -f "build.info" ]; then
  echo "init ngrok server!"
  DOMAIN=$1
  HTTP_PORT=$2
  HTTPS_PORT=$3
  TUNNEL_PORT=$4

  openssl genrsa -out rootCA.key 2048
  openssl req -x509 -new -nodes -key rootCA.key -subj "/CN=$DOMAIN" -days 5000 -out rootCA.pem
  openssl genrsa -out device.key 2048
  openssl req -new -key device.key -subj "/CN=$DOMAIN" -out device.csr
  openssl x509 -req -in device.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out device.crt -days 5000 

  \cp rootCA.pem assets/client/tls/ngrokroot.crt
  \cp device.crt assets/server/tls/snakeoil.crt
  \cp device.key assets/server/tls/snakeoil.key 

  make release-server
  make release-client
  GOOS=windows GOARCH=amd64 make release-client
  GOOS=windows GOARCH=386 make release-client  
  GOOS=darwin GOARCH=amd64 make release-client

  # save build info to file
  echo "$DOMAIN" >> build.info
  echo "$HTTP_PORT" >> build.info
  echo "$HTTPS_PORT" >> build.info
  echo "$TUNNEL_PORT" >> build.info
fi

# start ngrok server
DOMAIN=$(sed -n "1p" build.info)
HTTP_PORT=$(sed -n "2p" build.info)
HTTPS_PORT=$(sed -n "3p" build.info)
TUNNEL_PORT=$(sed -n "4p" build.info)

./bin/ngrokd -tlsKey=device.key -tlsCrt=device.crt -domain="$DOMAIN" -httpAddr=":$HTTP_PORT" -httpsAddr=":$HTTPS_PORT" -tunnelAddr=":$TUNNEL_PORT"
