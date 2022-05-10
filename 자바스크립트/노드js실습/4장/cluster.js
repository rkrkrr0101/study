const http=require('http')
const cluster=require('cluster')
const numcpu=require('os').cpus().length

if(cluster.isMaster){
	console.log(`마스터아이디:${process.pid}`)
	for (let i=0;i<numcpu;i+=1){
		cluster.fork()
	}

	cluster.on('exit',(worker,code,signal)=>{
		console.log(`${worker.process.pid}종료`)
		console.log('code',code,'sig',signal)
	})
}else{
	http.createServer((req,res)=>{
		res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
		res.write('<h1>henode</h1>')
		res.end('<p>helloserver</p>')
		
	})
		.listen(8080)	
	console.log(`${process.pid}번 실행`)
}
