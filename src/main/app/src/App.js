import {BrowserRouter, Route, Routes} from "react-router-dom";
import Test from "./components/test";

function App() {
  return (
      <BrowserRouter basename={process.env.PUBLIC_URL}>
          <Routes>
              <Route index element={<Test />} />
              <Route path="/test" element={<div>Test</div>} />
          </Routes>
      </BrowserRouter>
  );
}
export default App;
