import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { useNotification } from '../hooks/useNotification'

export default function LoginPage() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const { login } = useAuth()
  const navigate = useNavigate()
  const { notify, Notification } = useNotification()

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    try {
      await login(username, password)
      notify('Logged in')
      navigate('/products')
    } catch (e: any) {
      notify('Login failed')
    }
  }

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl mb-4">Login</h1>
      <form onSubmit={handleSubmit} className="space-y-3 bg-white p-4 rounded">
        <div>
          <label className="block text-sm">Username</label>
          <input value={username} onChange={e => setUsername(e.target.value)} className="w-full p-2 border rounded" />
        </div>
        <div>
          <label className="block text-sm">Password</label>
          <input type="password" value={password} onChange={e => setPassword(e.target.value)} className="w-full p-2 border rounded" />
        </div>
        <div className="flex gap-2">
          <button className="px-3 py-1 bg-indigo-600 text-white rounded">Login</button>
          <button type="button" onClick={() => { setUsername('testuser'); setPassword('password123') }} className="px-3 py-1 border rounded">Fill demo</button>
        </div>
      </form>
      <Notification />
    </div>
  )
}
