const fs=require('fs')
const zlib=require('zlib')

const readStream=fs.createReadStream('readme3.txt')
const zs=zlib.createGzip()
const WriteStream=fs.createWriteStream('writeme3.txt')
readStream.pipe(zs).pipe(WriteStream)