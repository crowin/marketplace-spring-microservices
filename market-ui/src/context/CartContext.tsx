import React, { createContext, useContext, useEffect, useState } from 'react'
import { getCart, addToCart as apiAdd, removeFromCart as apiRemove, clearCart as apiClear } from '../api'
import { CartDto, ItemCartDto } from '../types'

type CartContextType = {
  cart: CartDto | null
  cartCount: number
  refreshCart: () => Promise<void>
  addToCart: (item: ItemCartDto) => Promise<void>
  removeFromCart: (productId: number) => Promise<void>
  clearCart: () => Promise<void>
}

const CartContext = createContext<CartContextType | undefined>(undefined)

export const CartProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [cart, setCart] = useState<CartDto | null>(null)

  async function refreshCart() {
    try {
      const res = await getCart()
      setCart(res.data ?? null)
    } catch (e) {
      setCart(null)
    }
  }

  async function addToCart(item: ItemCartDto) {
    await apiAdd(item)
    await refreshCart()
  }

  async function removeFromCart(productId: number) {
    await apiRemove(productId)
    await refreshCart()
  }

  async function clearCart() {
    await apiClear()
    await refreshCart()
  }

  useEffect(() => {
    refreshCart()
  }, [])

  const cartCount = cart?.items?.reduce((s, it) => s + (it.quantity || 0), 0) ?? 0

  return (
    <CartContext.Provider value={{ cart, cartCount, refreshCart, addToCart, removeFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  )
}

export function useCart() {
  const ctx = useContext(CartContext)
  if (!ctx) throw new Error('useCart must be used within CartProvider')
  return ctx
}
