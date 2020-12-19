
import './App.css';
import ColorContext, { ColorProvider } from './cont/Color';
import ColorBox from './cont/ColorBox';
import SelectColor from './cont/SelectColor';

function App() {
  return (
    <ColorProvider >
      <div className="App">
        <SelectColor></SelectColor>
        <ColorBox></ColorBox>
      </div>
    </ColorProvider>
  );
}

export default App;
