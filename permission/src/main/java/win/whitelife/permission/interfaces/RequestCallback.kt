package win.whitelife.permission.interfaces

/**
 * wuzefeng
 * 2018/4/28
 */
interface RequestCallback {
//
//  /**
//   * 请求通过
//   */
//  fun granter(permission :String)
//
//
//  /**
//   * 请求被拒接
//   */
//  fun denied(permission :String)
//
//
//  /**
//   * 请求被取消
//   */
//  fun cancel(permission :String)

  /**
   * 请求结束
   */
  fun requestFinish(permissions: Array<String>?,grantResults: IntArray?)
}