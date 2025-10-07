import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import ProductsPage from './pages/Products'
import CartPage from './pages/Cart'
import OrdersPage from './pages/Orders'
import LoginPage from './pages/Login'
import RegisterPage from './pages/Register'
import Header from './components/Header'
import { useAuth } from './context/AuthContext'

export default function App() {
  const { loading } = useAuth()
  if (loading) return <div className="p-8">Loading...</div>

  return (
    <div className="min-h-screen">
      <Header />
      <main className="max-w-4xl mx-auto p-4">
        <Routes>
          <Route path="/" element={<Navigate to="/products" replace />} />
          <Route path="/products" element={<ProductsPage />} />
          <Route path="/cart" element={<CartPage />} />
          <Route path="/orders" element={<OrdersPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </main>
    </div>
  )
}
