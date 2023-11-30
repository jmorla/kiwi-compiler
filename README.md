
# Kiwi Compiler
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

Kiwi is a React code generator designed for Java template engines. It simplifies the integration of React components within Java-based applications. This README provides an overview of the Kiwi project, its purpose, and an example of its usage.

## Overview

### Purpose

Kiwi aims to streamline the process of incorporating React components into Java-based projects that utilize template engines. By generating JavaScript code from provided HTML source files, Kiwi facilitates the seamless integration of React components, enhancing the development workflow for Java developers working with modern web technologies.

## Example


### template html
```html
<!doctype html>
<html lang="en">
<head>
    <title>Kiwi | Sample</title>
    <link rel="stylesheet" href="/sample.bundle.css">
    <script defer src="/sample.bundle.js"></script>
    @import('./components/Button', 'Button')
</head>
<body>
    @render(<Button variant="primary" text="Hello world"/>)
</body>
</html>
```


### generated html
```html
<!doctype html>
<html lang="en">
<head>
    <title>Kiwi | Sample</title>
    <link rel="stylesheet" href="/sample.bundle.css">
    <script defer src="/sample.bundle.js"></script>
</head>
<body>
<div data-kiwi-id="button-2494b5a1" data-prop-variant="primary" data-prop-text="Hello world"></div>
</body>
</html>
```


### generated javascript
```javascript
import { createRoot } from 'react-dom/client';
import Button from './components/Button';

const button2494b5a1Node = document.getElementById('button-2494b5a1');
const button2494b5a1Root = createRoot(button2494b5a1Node);

button2494b5a1Root.render(<Button variant={button2494b5a1Node.getAttribute('data-prop-variant')} text={button2494b5a1Node.getAttribute('data-prop-text')} />)
```


## License

This project is licensed under the MIT License. Feel free to explore, use, and contribute to Kiwi!

