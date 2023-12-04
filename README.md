
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
    @import('./components/Button', 'Button')
    <script @import="./components/Button" @as="Button"></script> <!--experimental-->
</head>
<body>
    @render(<Button variant="primary" text="Hello world"/>)
    <div 
        @component="Button"
        @variant="primary" 
        @text="Hello world">
    </div> <!--experimental-->
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
    <script defer src="/react.bundle.js"></script>
    <script defer src="/sample.bundle.js"></script>
</head>
<body>
    <div data-kiwi-id="button-2494b5a1" 
        data-prop-variant="primary" 
        data-prop-text="Hello world">
    </div>
</body>
</html>
```


### generated bundle
```bash
/path/to/compiled/react.bundle.js
/path/to/compiled/sample.bundle.js
/path/to/compiled/sample.bundle.css
```


## License

This project is licensed under the MIT License. Feel free to explore, use, and contribute to Kiwi!

