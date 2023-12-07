
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
    @import('./components/UserDetailsForm', 'UserDetailsForm')
    <!--experimental-->
    <script @import="./components/UserDetailsForm" @as="UserDetailsForm"></script> 
</head>
<body>
    {{#user}}
    @render(<UserDetailsForm userId="{{id}}" />)
    <!--experimental-->
    <div @component="UserDetailsForm" userId="{{id}}"></div>
    {{/user}}
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
    {{#user}}
    <div data-kiwi-id="UserDetailsForm-2494b5a1" data-prop-userId="{{id}}"></div>
    {{/user}}
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

