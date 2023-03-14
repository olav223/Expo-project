import {Route, Routes} from "react-router-dom";
import Test from "./components/test";


function App() {
  return (
      <Routes>
          <Route index element={<Test />} />
          <Route path="test" element={<div>Test</div>} />
      </Routes>
  );
}
export default App;
