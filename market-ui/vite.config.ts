import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Dev server runs on localhost:8080. Proxy /users -> http://localhost:8085/users and /market -> http://localhost:8085/market
export default defineConfig({
  plugins: [react()],
  server: {
    port: 8080,
    proxy: {
      '/users': {
        target: 'http://localhost:8085',
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/users/, '/users')
      },
      '/market': {
        target: 'http://localhost:8085',
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/market/, '/market')
      }
    }
  }
})
