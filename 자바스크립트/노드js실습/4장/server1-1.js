const http=require('http')

const server=http.createServer((req,res)=>{
	res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
	res.write('<h1>henode</h1>')
	res.end('<p>helloserver</p>')
	
})
server.listen(8080)

server.on('listening',()=>{
	console.log('adsd')
})
server.on('error',()=>{
	console.error(error)
})