const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const merge = require('webpack-merge');
const validate = require('webpack-validator');
const parts = require('./libs/parts');
const pkg = require('./package.json');

const PATHS = {
  css: path.join(__dirname, 'app', 'css'),
  js: path.join(__dirname, 'app', 'js'),
  app: path.join(__dirname, 'app'),
  build: path.join(__dirname, 'build')
};

const common = {
    entry: {
      app: PATHS.js,
      vendor: Object.keys(pkg.dependencies)
    },
    output: {
        path: PATHS.build,
        filename: '[name].js'
    },
    module: {
      loaders: [{
        test: /\.jsx?$/,            // match both .js and .jsx files
        exclude: /node_modules/,
        loader: 'babel',
        query: {
          presets: ["es2015", 'react']
        }
      }]
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: 'Global'
        })
    ]
};

var config;

switch(process.env.npm_lifecycle_event) {
    case 'build':
        config = merge(
            common,
            parts.clean(PATHS.build),
            {
                devtool: "source-map",
                output: {
                    path: PATHS.build,
                    filename: '[name].[chunkhash].js',
                    chunkFilename: '[chunkhash].js'
                }
            },
            parts.setFreeVariable(
                'process.env.NODE_ENV',
                'production'
            ),
            parts.extractBundle({
                name: 'vendor',
                entries: ['react']
            }),
            parts.minify(),
            parts.setupCSS(PATHS.css)
        );
        break;
    default:
        config = merge(
            common,
            {
                devtool: "eval-source-map"
            },
            parts.setupCSS(PATHS.app),
            parts.devServer({
                host: process.env.HOST,
                host: process.env.PORT
            })
        );
}

module.exports = validate(config);
