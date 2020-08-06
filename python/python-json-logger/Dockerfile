# Copyright 2020 Yoshi Yamaguchi
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM python:3.7-slim-buster as builder
WORKDIR /app
COPY requirements.txt /app
RUN pip3 install -r requirements.txt

FROM gcr.io/distroless/python3-debian10
LABEL maintainer "Yoshi Yamaguchi <@ymotongpoo>"
WORKDIR /app
COPY --from=builder /usr/local/lib/python3.7/site-packages /root/.local/lib/python3.7/site-packages
COPY main.py /app
CMD ["main.py"]