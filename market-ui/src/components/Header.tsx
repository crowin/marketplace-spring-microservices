import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { useCart } from '../context/CartContext'

export default function Header() {
  const { token, logout } = useAuth()
  const navigate = useNavigate()
  const { cartCount, refreshCart } = useCart()

  return (
    <header className="bg-white shadow">
      <div className="max-w-4xl mx-auto p-4 flex items-center justify-between gap-4">
        <Link to="/products" className="flex items-center gap-3">
          <div className="w-10 h-10 bg-indigo-600 rounded flex items-center justify-center text-white font-bold">M</div>
          <div>
            <div className="text-lg font-semibold">Market SPA</div>
            <div className="text-xs text-slate-500">Simple demo storefront</div>
          </div>
        </Link>

        <nav className="flex items-center gap-3" data-test-id="header-nav">
          <Link to="/products" className="px-3 py-2 rounded hover:bg-slate-100" data-test-id="products-link">Products</Link>
          <Link to="/orders" className="px-3 py-2 rounded hover:bg-slate-100" data-test-id="orders-link">Orders</Link>
          <Link to="/cart" onClick={refreshCart} className="px-3 py-2 rounded hover:bg-slate-100"  data-test-id="cart-link">
            Cart {cartCount > 0 ? `(${cartCount})` : ''}
          </Link>
          {token ? (
            <button onClick={() => { logout(); navigate('/login') }} className="px-3 py-2 bg-slate-100 rounded">
              Logout
            </button>
          ) : (
            <>
              <Link to="/login" className="px-3 py-2 rounded hover:bg-slate-100"  data-test-id="login-link">Login</Link>
              <Link to="/register" className="px-3 py-2 rounded hover:bg-slate-100"  data-test-id="register-link">Register</Link>
            </>
          )}
        </nav>
      </div>
    </header>
  )
}
