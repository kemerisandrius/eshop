import React from 'react'
import Footer from './components/Footer'
import Header from './components/Header'
import Menu from "./components/Menu";
import Content from "./components/Content";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  return (
    <Router>
        <Header />
        <Menu />
        <Content />
        <Footer />
    </Router>
  )
}

export default App
