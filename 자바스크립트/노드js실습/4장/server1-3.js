const http=require('http')

http.createServer((req,res)=>{
	res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
	res.write('<h1>henode</h1>')
	res.end('<p>helloserver</p>')
	
})
	.listen(8080,()=>{
		console.log('8080open')
	})