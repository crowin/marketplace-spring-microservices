/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        './index.html',
        './src/**/*.{ts,tsx,js,jsx,css}', // add CSS, to @apply be visible
    ],
    theme: {
        extend: {},
    },
    plugins: [],
}
