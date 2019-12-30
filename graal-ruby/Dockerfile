FROM oracle/graalvm-ce:19.3.0-java11

RUN yum update -y
RUN gu install ruby
RUN yum install git -y
RUN yum install libxml2-devel -y
RUN yum install libxslt-devel -y
RUN gem install ruby_danfe
