/*
 * Copyright 2018 org.LTR4L
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ltr4l.boosting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ltr4l.Ranker;
import org.ltr4l.tools.Config;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class RankBoost extends Ranker<RankBoost.RankBoostConfig> {
  private final List<WeakLearner> learners;

  public RankBoost(){
    learners = new ArrayList<>();
  }

  public void addLearner(WeakLearner wl){
    learners.add(wl);
  }

  @Override
  public void writeModel(RankBoostConfig config, Writer writer) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public double predict(List<Double> features) {
    return learners.stream().mapToDouble(wl -> wl.predict(features)).sum();
  }

  public static class RankBoostConfig extends Config {
    @JsonIgnore
    public int getNumSteps() { return getInt(params, "numSteps", 0); } //TODO: OK default value?
  }
}
