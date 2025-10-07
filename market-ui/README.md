# SPA Market (Frontend)

Simple React + Vite + TypeScript + Tailwind SPA that integrates with two backend services described by the provided OpenAPI files:

- Users service (auth): expected at http://localhost:8081/users
- Market service (products/cart/orders): expected at http://localhost:8082/market

However the frontend runs on http://localhost:8080 and the dev server proxies requests:
- `/users/*` -> `http://localhost:8081/users/*`
- `/market/*` -> `http://localhost:8082/market/*`

## Features
- Register / Login
- List products (paginated)
- Add product to cart, remove item, clear cart
- View cart and place an order
- View orders

## How to run (development)
1. Install dependencies:
   ```bash
   npm install
   ```
2. Run the dev server:
   ```bash
   npm run dev
   ```
   The app will be available at http://localhost:8080

Make sure backend services are running on ports 8081 and 8082 as in the original OpenAPI docs.

## Notes for backend developer (minimal frontend knowledge)
- The SPA uses simple `fetch`-based API functions in `src/api.ts` that map directly to the documented OpenAPI endpoints.
- Token is stored in `localStorage` as `token` and added as `Authorization: Bearer <token>` header when present.
- If you need to change header names or token handling, edit `src/api.ts` only.
- UI consists of simple React components using Tailwind classes for fast visual polish.

## Production build
```bash
npm run build
npm run preview
```

## Files of interest
- `src/api.ts` — API client (map to OpenAPI)
- `src/types.ts` — types matched to API DTOs
- `src/pages/*` — pages (Products, Cart, Orders, Login, Register)
- `vite.config.ts` — proxy configuration to backend services
