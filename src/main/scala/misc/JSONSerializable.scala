/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package misc

import scala.util.parsing.json.JSONObject

trait JSONSerializable[A] {

  def fromJSON(json: JSONObject)(implicit format: JSONFormat[A]): Option[A] =
    format fromJSON json

  implicit class JSONOps[A](a: A) {
    def toJSON(implicit format: JSONFormat[A]): JSONObject =
      format toJSON a
  }
}

trait JSONFormat[A] {

  def toJSON(a: A): JSONObject

  def fromJSON(json: JSONObject): Option[A]
}
