import React from 'react'
import { useCart } from '../context/CartContext'
import { useNotification } from '../hooks/useNotification'
import { placeOrder as apiPlaceOrder } from '../api'

export default function CartPage() {
  const { cart, removeFromCart, clearCart } = useCart()
  const { notify, Notification } = useNotification()

  async function handleClear() {
    await clearCart()
    notify('Cart cleared')
  }

  async function handleRemove(productId: number) {
    await removeFromCart(productId)
    notify('Item removed')
  }

  async function handlePlaceOrder() {
    try {
      const res = await apiPlaceOrder()
      if (res?.data) {
        notify('Order placed: #' + res.data.id)
      } else {
        notify('Failed to place order')
      }
    } catch (e) {
      notify('Failed to place order')
    }
  }

  if (!cart) return <div>Loading cart...</div>

  return (
    <div>
      <h1 className="text-2xl mb-4">Your Cart</h1>
      {cart.items.length === 0 ? <div>Your cart is empty</div> : (
        <>
          <div className="space-y-3">
            {cart.items.map(it => (
              <div key={it.product.id} className="p-3 bg-white rounded flex justify-between items-center">
                <div>
                  <div className="font-semibold">{it.product.title}</div>
                  <div className="text-sm text-slate-600">Qty: {it.quantity} • Price: {it.product.price.toFixed(2)}</div>
                </div>
                <div className="flex gap-2 items-center">
                  <div className="font-medium">{it.totalPrice.toFixed(2)}</div>
                  <button onClick={() => handleRemove(it.product.id)} className="px-3 py-1 border rounded">Remove</button>
                </div>
              </div>
            ))}
          </div>
          <div className="mt-4 flex justify-between items-center">
            <div className="text-lg font-semibold">Total: {cart.totalPrice?.toFixed(2)}</div>
            <div className="flex gap-2">
              <button onClick={handleClear} className="px-3 py-1 rounded border">Clear cart</button>
              <button onClick={handlePlaceOrder} className="px-3 py-1 bg-indigo-600 text-white rounded">Place order</button>
            </div>
          </div>
        </>
      )}
      <Notification />
    </div>
  )
}
