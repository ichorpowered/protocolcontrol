/*
 * This file is part of ProtocolControl, licensed under the MIT License (MIT).
 *
 * Copyright (c) IchorPowered <http://ichorpowered.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ichorpowered.protocolcontrol.event;

import com.google.common.reflect.TypeToken;
import com.ichorpowered.protocolcontrol.channel.ChannelProfile;
import com.ichorpowered.protocolcontrol.packet.PacketDirection;
import net.kyori.event.ReifiedEvent;
import net.minecraft.network.Packet;
import org.checkerframework.checker.nullness.qual.NonNull;

import static java.util.Objects.requireNonNull;

/**
 * An event containing the {@code T} packet, the {@link PacketDirection}
 * and the {@link ChannelProfile} which it applies to.
 *
 * @param <T> the packet type
 */
public final class PacketEvent<T extends Packet<?>> implements ReifiedEvent<T> {
  private final ChannelProfile profile;
  private final PacketDirection direction;
  private T packet;
  private boolean cancel = false;

  public PacketEvent(final @NonNull ChannelProfile profile,
                     final @NonNull PacketDirection direction,
                     final @NonNull T packet) {
    this.profile = requireNonNull(profile, "profile");
    this.direction = requireNonNull(direction, "direction");
    this.packet = requireNonNull(packet, "packet");
  }

  /**
   * Returns the {@link ChannelProfile} the packet applies to.
   *
   * @return the channel profile
   */
  public @NonNull ChannelProfile profile() {
    return this.profile;
  }

  /**
   * Returns the {@link PacketDirection} the packet applies to.
   *
   * @return the packet direction
   */
  public @NonNull PacketDirection direction() {
    return this.direction;
  }

  /**
   * Returns the {@code T} packet object.
   *
   * @return the packet object
   */
  public @NonNull T packet() {
    return this.packet;
  }

  /**
   * Sets the {@code T} packet object.
   *
   * @param packet the packet object
   */
  public void packet(final @NonNull T packet) {
    this.packet = requireNonNull(packet, "packet");
  }

  /**
   * Returns {@code true} if the packet
   * should cancel.
   *
   * @return whether the packet should be cancelled
   */
  public boolean cancel() {
    return this.cancel;
  }

  /**
   * Sets whether the packet should cancel.
   *
   * @param cancel whether the packet should cancel
   */
  public void cancel(final boolean cancel) {
    this.cancel = cancel;
  }

  @SuppressWarnings({"unchecked", "UnstableApiUsage"})
  @Override
  public @NonNull TypeToken<T> type() {
    return (TypeToken<T>) TypeToken.of(this.packet.getClass());
  }
}
