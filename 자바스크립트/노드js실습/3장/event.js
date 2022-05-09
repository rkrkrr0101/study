const EventEmitter=require('events')

const me= new EventEmitter()
me.addListener('event1',()=>{ //이벤트이름과 액션을 연결
	console.log('이벤트1')
})
me.on('event2',()=>{ //이벤트이름과 액션을 연결,addListener와 같음
	console.log('이벤트2')
})
me.once('event3',()=>{ //이벤트이름과 액션을 연결,이벤트가 한번만 발생,한이벤트에 여러개가 묶일수있음
	console.log('이벤트3')
})	
me.emit('event5')//이벤트호출
me.removeAllListeners('event3')//이벤트명의 모든 이벤트를 삭제함
const li=()=>{
	console.log('이벤트5')
}
me.on('event5',li)//이벤트와 리스너연결
//me.removeListeners('이벤트명',리스너변수)//이벤트에서 특정리스너제거
//me.off('이벤트명',리스너변수)//removeListeners와 같음
console.log(me.listenerCount('event5'))//이벤트에 연결된 리스너갯수리턴
me.emit('event5')//이벤트호출