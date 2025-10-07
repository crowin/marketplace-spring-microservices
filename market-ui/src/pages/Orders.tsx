import React, { useEffect, useState } from 'react'
import { getOrders } from '../api'
import { OrderDto } from '../types'

export default function OrdersPage() {
  const [orders, setOrders] = useState<OrderDto[]>([])
  const [loading, setLoading] = useState(true)

  async function load() {
    setLoading(true)
    try {
      const res = await getOrders(0, 10)
      setOrders(res?.data?.items || [])
    } catch (e) {
      // ignore
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => { load() }, [])

  return (
    <div>
      <h1 className="text-2xl mb-4">Your Orders</h1>
      {loading ? <div>Loading...</div> : (
        <>
          {orders.length === 0 ? <div>No orders yet.</div> : (
            <div className="space-y-3">
              {orders.map(o => (
                <div className="bg-white p-3 rounded" key={o.id}>
                  <div className="flex justify-between">
                    <div className="font-semibold">Order #{o.id}</div>
                    <div className="text-sm text-slate-600">{o.status}</div>
                  </div>
                  <div className="text-sm text-slate-600">Created: {o.createdAt}</div>
                  <div className="mt-2">
                    {(o.items?.products || []).map(it => (
                      <div key={it.product.id} className="flex justify-between">
                        <div>{it.product.title} x {it.quantity}</div>
                        <div>{it.totalPrice.toFixed(2)}</div>
                      </div>
                    ))}
                  </div>
                </div>
              ))}
            </div>
          )}
        </>
      )}
    </div>
  )
}
