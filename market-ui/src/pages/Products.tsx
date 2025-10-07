import React, { useEffect, useState } from 'react'
import { getProducts } from '../api'
import { ProductDto } from '../types'
import ProductCard from '../components/ProductCard'

export default function ProductsPage() {
  const [items, setItems] = useState<ProductDto[]>([])
  const [page, setPage] = useState(0)
  const [totalPages, setTotal] = useState(0)
  const [loading, setLoading] = useState(true)

  async function load() {
    setLoading(true)
    try {
      const res = await getProducts(page, 8)
      setItems(res?.data?.items || [])
      setTotal(res?.data?.totalPages || 0)
    } catch (e) {
      // ignore
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => { load() }, [page])

  return (
    <div>
      <h1 className="text-2xl mb-4">Products</h1>
      {loading ? <div>Loading products...</div> : (
        <>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            {items.map(p => <ProductCard key={p.id} product={p} />)}
          </div>
          <div className="mt-6 flex justify-between items-center">
            <div>Page {page + 1} of {Math.max(1, totalPages)}</div>
            <div className="flex gap-2">
              <button onClick={() => setPage(p => Math.max(0, p - 1))} className="px-3 py-1 border rounded">Prev</button>
              <button onClick={() => setPage(p => p + 1)} className="px-3 py-1 border rounded">Next</button>
            </div>
          </div>
        </>
      )}
    </div>
  )
}
