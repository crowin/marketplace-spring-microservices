import React, { useState } from 'react'
import { ProductDto } from '../types'
import { useNotification } from '../hooks/useNotification'
import { useCart } from '../context/CartContext'

export default function ProductCard({ product }: { product: ProductDto }) {
  const [qty, setQty] = useState(1)
  const [loading, setLoading] = useState(false)
  const { notify, Notification } = useNotification()
  const { addToCart } = useCart()

  async function handleAdd() {
    try {
      setLoading(true)
      await addToCart({ itemId: product.id, quantity: qty })
      notify('Added to cart')
    } catch (e: any) {
      notify('Failed to add to cart')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="p-4 border rounded bg-white relative">
      <div className="font-semibold">{product.title}</div>
      <div className="text-sm text-slate-600">Price: {product.price.toFixed(2)}</div>
      <div className="mt-3 flex items-center gap-2">
        <input type="number" min={1} value={qty} onChange={(e) => setQty(Math.max(1, Number(e.target.value || 1)))} className="w-20 p-1 border rounded" />
        <button onClick={handleAdd} disabled={loading} className="px-3 py-1 bg-indigo-600 text-white rounded">
          {loading ? 'Adding...' : 'Add to cart'}
        </button>
      </div>
      <Notification />
    </div>
  )
}
