/**
 * Copyright (C) 2009-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.actor.testkit.typed.javadsl

import java.time.Duration

import akka.actor.typed.{ ActorRef, Behavior, Props }
import akka.actor.testkit.typed.Effect
import akka.util.JavaDurationConverters._

/**
 * Factories for behavior effects for [[BehaviorTestKit]], each effect has a suitable equals and can be used to compare
 * actual effects to expected ones.
 */
object Effects {
  import akka.actor.testkit.typed.Effect._

  /**
   * The behavior spawned a named child with the given behavior with no specific props
   */
  def spawned[T](behavior: Behavior[T], childName: String): Spawned[T] = Spawned(behavior, childName)
  /**
   * The behavior spawned a named child with the given behavior with no specific props
   */
  def spawned[T](behavior: Behavior[T], childName: String, ref: ActorRef[T]): Spawned[T] = new Spawned(behavior, childName, Props.empty, ref)
  /**
   * The behavior spawned a named child with the given behavior and specific props
   */
  def spawned[T](behavior: Behavior[T], childName: String, props: Props): Spawned[T] = Spawned(behavior, childName, props)
  /**
   * The behavior spawned a named child with the given behavior and specific props
   */
  def spawned[T](behavior: Behavior[T], childName: String, props: Props, ref: ActorRef[T]): Spawned[T] = new Spawned(behavior, childName, props, ref)
  /**
   * The behavior spawned an anonymous child with the given behavior with no specific props
   */
  def spawnedAnonymous[T](behavior: Behavior[T]): SpawnedAnonymous[T] = SpawnedAnonymous(behavior)
  /**
   * The behavior spawned an anonymous child with the given behavior with no specific props
   */
  def spawnedAnonymous[T](behavior: Behavior[T], ref: ActorRef[T]): SpawnedAnonymous[T] = new SpawnedAnonymous(behavior, Props.empty, ref)
  /**
   * The behavior spawned an anonymous child with the given behavior with specific props
   */
  def spawnedAnonymous[T](behavior: Behavior[T], props: Props): SpawnedAnonymous[T] = SpawnedAnonymous(behavior, props)
  /**
   * The behavior spawned an anonymous child with the given behavior with specific props
   */
  def spawnedAnonymous[T](behavior: Behavior[T], props: Props, ref: ActorRef[T]): SpawnedAnonymous[T] = new SpawnedAnonymous(behavior, props, ref)
  /**
   * The behavior stopped `childName`
   */
  def stopped(childName: String): Stopped = Stopped(childName)
  /**
   * The behavior started watching `other`, through `ctx.watch(other)`
   */
  def watched[T](other: ActorRef[T]): Watched[T] = Watched(other)

  /**
   * The behavior started watching `other`, through `ctx.unwatch(other)`
   */
  def unwatched[T](other: ActorRef[T]): Unwatched[T] = Unwatched(other)

  /**
   * The behavior set a new receive timeout, with `msg` as timeout notification
   */
  def receiveTimeoutSet[T](d: Duration, msg: T): ReceiveTimeoutSet[T] = ReceiveTimeoutSet(d.asScala, msg)

  /**
   * The behavior used `ctx.schedule` to schedule `msg` to be sent to `target` after `delay`
   * FIXME what about events scheduled through the scheduler?
   */
  def scheduled[U](delay: Duration, target: ActorRef[U], msg: U): Scheduled[U] =
    Scheduled(delay.asScala, target, msg)

  /**
   * Used to represent an empty list of effects - in other words, the behavior didn't do anything observable
   */
  def noEffects(): NoEffects = NoEffects

}
