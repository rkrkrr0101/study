const fs=require('fs')

const readStream=fs.createReadStream('readme3.txt')
const WriteStream=fs.createWriteStream('writeme3.txt')
readStream.pipe(WriteStream)