import React, { useState } from 'react'

export function useNotification() {
  const [msg, setMsg] = useState<string | null>(null)
  const notify = (m: string) => {
    setMsg(m)
    setTimeout(() => setMsg(null), 3000)
  }
  const Notification = () => msg ? (
    <div className="fixed bottom-4 right-4 bg-slate-900 text-white px-4 py-2 rounded shadow" data-test-id="notification">
      {msg}
    </div>
  ) : null

  return { notify, Notification }
}
