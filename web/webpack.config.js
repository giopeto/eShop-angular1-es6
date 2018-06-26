const path = require("path");
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
	mode: 'development',
	entry: {
		app: "./src/app.js",
	},
	output: {
		path: path.resolve(__dirname, "public/js"),
		filename: "[name].bundle.js"
	},
	module: {
		rules: [{
			test: /\.js$/,
			exclude: /(node_modules)/,
			use: {
				loader: "babel-loader",
				options: {
					presets: ["babel-preset-env"]
				}
			}

		}, {
			test:  /\.(png|svg|jpg|gif)$/,
			use: [
				{
					loader: 'file-loader'
				}
			]
		}, {
			test: /\.html$/, loader: "html-loader"
		}, {
			test: /\.css$/,
			use: [ 'style-loader', 'css-loader' ]
		}]
	}, plugins: [
		new CleanWebpackPlugin(['public'])
	]
};