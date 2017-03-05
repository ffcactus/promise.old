'use strict';

var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
  devtool: 'eval-source-map',
  entry: [
    'webpack-dev-server/client?http://localhost:3000',
    'webpack/hot/only-dev-server',
    'react-hot-loader/patch',
    path.join(__dirname, 'app/index.js')
  ],
  output: {
    path: path.join(__dirname, 'public'),
    filename: 'index.js',
    publicPath: '/public'
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'app/index.tpl.html',
      inject: 'body',
      filename: 'index.html'
    }),
    new ExtractTextPlugin('style.css', { allChunks: true }),
    new webpack.optimize.OccurenceOrderPlugin(),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin(),
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify('development')
    })
  ],
  eslint: {
    configFile: '.eslintrc',
    failOnWarning: false,
    failOnError: false
  },
  postcss: [
    require('autoprefixer-core'),
    require('postcss-color-rebeccapurple')
  ],
  module: {
    // preLoaders: [
    //     {
    //         test: /\.js$/,
    //         exclude: /node_modules/,
    //         loader: 'eslint'
    //     }
    // ],
    loaders: [
      { test: /\.js?$/, exclude: /node_modules/, loader: 'babel' },
      { test: /\.json?$/, loader: 'json' },
      { test: /\.css$/, loader: ExtractTextPlugin.extract('style-loader', 'css-loader?modules&importLoaders=1&localIdentName=[name]__[local]___[hash:base64:5]!postcss-loader') },
      { test: /\.woff(2)?(\?[a-z0-9#=&.]+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff' },
      { test: /\.(ttf|eot|svg)(\?[a-z0-9#=&.]+)?$/, loader: 'file' }
    ]
  }
};
