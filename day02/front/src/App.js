import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import {TableList} from './pages/TableList'
import {TableDetail} from './pages/TableDetail'

function App() {
    return (
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<TableList/>}></Route>
              <Route path="/:tableId" element={<TableDetail/>}></Route>
            </Routes>
          </BrowserRouter>
    );
}

export default App;