# layout.components

## index.js 文件

```js
// index.js
export { default as Navbar } from './Navbar'
export { default as Sidebar } from './Sidebar'
export { default as AppMain } from './AppMain'
```

 JavaScript ES6 中的模块语法，用于从一个模块中重新导出其默认导出。

这里的语句做了三件事：

1. **导入默认导出**：从当前目录下的 'Navbar' 文件（我们假设这是一个 '.js' 或 '.vue' 文件）导入默认导出。
2. **重命名默认导出**：把这个默认导出重命名为 'Navbar'。
3. **重新导出**：将这个 'Navbar' 再次导出，以便其他模块可以导入。

这种语法通常在你希望从一个模块重新导出另一个模块的导出时使用。这样可以让其他模块只需要从一个地方导入所有需要的东西，而不需要知道具体的文件结构。

然后在其他模块，你可以这样导入这些组件：

```js
import { Navbar, Sidebar, AppMain } from './components'
```