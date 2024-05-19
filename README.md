
# Kiwi React Generator
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

Kiwi is a React code generator designed for Java template engines. It simplifies the integration of React components within Java-based applications. This README provides an overview of the Kiwi project, its purpose, and an example of its usage.

## Overview

### Purpose

Kiwi aims to streamline the process of incorporating React components into Java-based projects that utilize template engines. By generating JavaScript code from provided HTML source files, Kiwi facilitates the seamless integration of React components, enhancing the development workflow for Java developers working with modern web technologies.

## Example

### source

```jsx
import React from 'react';

export default function Greeting ({ message, value, clickable }) {
	return (<div>
		<p>Message: {message}</p>
		<p>Value: {value || 0}</p>
		<p>{`Is ${!clikable && "not"} clikable`}</p>
	</div>);
}
```

```java
var source = """
        @import('Greeting', './components/Greeting')
        @render(<Greeting message="Hello {{name}}!" value:num="10" />)
        @render(<Greeting message="Hello Strange" clickable:bool="true" />)
        """;

var generator = KiwiGenerator.withDefault();
String output = generator.generateJs(source);
String htmlOutput = generator.generateHtml(source);
```


### generated html
```html
<div data-kiwi-id="Greeting1111559717" data-prop-message="Hello {{name}}!" data-prop-value="10"></div>
<div data-kiwi-id="Greeting626337202" data-prop-message="Hello Strange" data-prop-clickable="true"></div>
```


### generated Js
```js
/*
    WARNING: AUTO-GENERATED CODE BY KIWI-GENERATOR
    Modifying this code may result in unintended behavior. 
    Please make changes in the source template or consult 
    the kiwi-react-generator documentation for customization options.
*/
import React from 'react';
import { createRoot } from 'react-dom/client';
import Greeting from './components/Greeting';

const greeting1111559717_elements = document.querySelectorAll('[data-kiwi-id="Greeting1111559717"]');
for(let element of greeting1111559717_elements) {
	const props = {
		message: (element.getAttribute('data-prop-message')),
		value: Number(element.getAttribute('data-prop-value')),
	};
	const root = createRoot(element);
	root.render(<Greeting {...props} />);
}

const greeting626337202_elements = document.querySelectorAll('[data-kiwi-id="Greeting626337202"]');
for(let element of greeting626337202_elements) {
	const props = {
		message: (element.getAttribute('data-prop-message')),
		clickable: 'true' == (element.getAttribute('data-prop-clickable')),
	};
	const root = createRoot(element);
	root.render(<Greeting {...props} />);
}
```


## License

This project is licensed under the MIT License. Feel free to explore, use, and contribute to Kiwi!

