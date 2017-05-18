%������ݿ�����
conn = database('ibook', 'root', '595322101asd', 'com.mysql.jdbc.Driver', 'jdbc:mysql://localhost:3305/ibook');
%��õ�ǰϵͳʱ��
currentTime=datestr(now,31);

%�����˲�����ͨ�˲�
[y,Fs] = audioread('m0.mp3');
mytest = resample(y,8000,Fs);
[N,Wc]=buttord(0.1,0.15,1,30);
[b,a]=butter(N,Wc);
temp=mytest(:,1)';
temp=abs(temp);
temp1=peace(temp,5);
[m,l]=max(temp1);
temp1=temp./m;
temp2 = 2*filter(b,a,temp1);

%���ö˵��⺯��
surplus=temp2';
endTime=0;

while(length(surplus)>1000)
    %���볤��
    inputLength=length(surplus);
    %vadȡ��һ��������Ƭ��
    [vadSnore,surplus]=vad(surplus);
    %ʣ�೤��
    surplusLength=length(surplus);
    %������Ƭ�εĳ���
    duration=length(vadSnore);
    %��ֹʱ���
    endTime=endTime+(inputLength-surplusLength);
    %��ʼʱ���
    startTime=endTime-duration;
    %�����ݿ��������
    if(duration/8000>0.6)
        insert(conn,'snore',{'startTime','endTime','duration','dateTime','meanValue','maximumValue'},...
            {startTime,endTime,duration,currentTime,mean(vadSnore),max(vadSnore)});
    end
end


figure(1)
subplot(3,1,1);
plot(temp2);
subplot(3,1,2);
plot(surplus);
subplot(3,1,3);
plot(vadSnore);


